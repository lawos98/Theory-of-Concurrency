var fs = require('fs');
var scs = require('scatter-chart-simple');

let steps=100
let k=500
let counter=0
let max_counter=0
let data1 = Array(k)
let data1time = Array(k)
let data2 = Array(k)
let data2time = Array(k)
data1.fill(0)
data1time.fill(0)
data2.fill(0)
data2time.fill(0)

let Fork = function() {
    this.state = 0;
    return this;
}

Fork.prototype.acquire = function(cb,philosopher) {
    let fork = this, delay = 1;

    let current_delay=Math.floor(Math.random() * delay);

    function acquire_fork() {
        if(fork.state === 0){
            fork.state = 1;
            if(cb)cb();
        }
        else {
            delay *= 2;
            current_delay=Math.floor(Math.random() * delay);
            philosopher.sum+=current_delay
            setTimeout(acquire_fork, current_delay);
        }
    }
    setTimeout(acquire_fork,delay)
}

Fork.prototype.release = function() {
    this.state = 0;
}

let Philosopher = function(id, forks) {
    this.id = id;
    this.forks = forks;
    this.f1 = id % forks.length;
    this.f2 = (id+1) % forks.length;
    this.sum=0;
    return this;
}

Philosopher.prototype.startAsym = function(count,size) {
    let forks = this.forks,
        f1 = this.f1,
        f2 = this.f2,
        id = this.id;

    let philosopher=this

    let acquireSecondFork = function(){
        let choiceFork= id % 2 === 0 ? f1 : f2;
        forks[choiceFork].acquire(releaseForks,philosopher);
    }

    let releaseForks = function () {
        forks[f1].release();
        forks[f2].release();
        setTimeout(loop,Math.floor(Math.random() * 1000))
    }


    let loop = function () {
        if (count-- > 0) {
            setTimeout(function(){
                let choiceFork= id % 2 !== 0 ? f1 : f2;
                forks[choiceFork].acquire(acquireSecondFork,philosopher)
            }, Math.floor(Math.random() * 1000));
        }
        if(count===0){
            data1time[size]+=philosopher.sum;
            data1[size]+=1;
        }
    };

    loop();
}

let Waiter = function(N) {
    this.slots = N-1;
    return this;
}

Waiter.prototype.acquire = function(cb,philosopher) {
    let waiter = this, delay = 1;

    let current_delay=Math.floor(Math.random() * delay);

    function acquire_table() {
        if(waiter.slots === 0){
            delay *= 2;
            current_delay=Math.floor(Math.random() * delay);
            philosopher.sum+=current_delay
            setTimeout(acquire_table,current_delay);
        }
        else {
            waiter.slots-=1;
            if (cb) cb();
        }
    }

    setTimeout(acquire_table,delay);
}

Waiter.prototype.release = function() {
    this.slots += 1;
}

Philosopher.prototype.startConductor = function(count,size,w) {
    let forks = this.forks,
        f1 = this.f1,
        f2 = this.f2,
        id = this.id;
    let philosopher=this
    let waiter=w

    let acquireRightFork = function(){
        forks[f1].acquire(acquireLeftFork,philosopher);
    }
    let acquireLeftFork = function(){
        forks[f2].acquire(releaseForks,philosopher);
    }
    let releaseForks = function () {
        forks[f1].release();
        forks[f2].release();
        waiter.release()
        setTimeout(loop,Math.floor(Math.random() * 1000))
    }

    let loop = function () {
        if (count > 0) {
            waiter.acquire(acquireRightFork,philosopher)
        }
        if(count===0){
            data2time[size]+=philosopher.sum;
            data2[size]+=1;
        }
        count-=1;
    };

    setTimeout(loop,Math.floor(Math.random() * 1000))
}

let philosophers1,philosophers2,forks1,forks2;
for(let N=5;N<k;N++){
    philosophers1 = []
    philosophers2 = []
    forks1 = []
    forks2 = []
    for (let i = 0; i < N; i++) {
        forks1.push(new Fork());
        forks2.push(new Fork());
    }
    for (let i = 0; i < N; i++) {
        philosophers1.push(new Philosopher(i, forks1));
        philosophers2.push(new Philosopher(i, forks2));
    }
    for (let i = 0; i < N; i++) {
        philosophers1[i].startAsym(steps,N);
        max_counter+=1;
    }
    let w=new Waiter(N)
    for (let i = 0; i < N; i++) {
        philosophers2[i].startConductor(steps,N,w);
        max_counter+=1;
    }
}

d1=[]
d2=[]

let showResults=function (){
    for(let i=5;i<k;i++){
        d1.push({x:i,y:data1time[i]/(data1[i]*steps)})
        d2.push({x:i,y:data2time[i]/(data2[i]*steps)})
    }
    let datasets=[{title:'sync',data:d1,color:'green'},{title:'waiter',data:d2,color:'red'}]
    let jsonData = JSON.stringify(datasets);
    fs.writeFile("result.txt", jsonData, function(err) {
        if (err) {
            console.log(err);
        }
    });
    scs.plotMultiDataChart("chart.png", datasets);
    console.log("Data Ready")
}

let check1 = function(){
    console.log(data1time)
    console.log(data2time)
    counter=0
    data1.forEach(x=>counter+=x)
    data2.forEach(x=>counter+=x)
    console.log(counter+' / '+ max_counter +' | '+Math.floor(10000*counter/max_counter)/100+' % ')
    if(counter===max_counter){
        showResults()
    }
    else{
        setTimeout(check1,10000)
    }
}

setTimeout(check1,10000)