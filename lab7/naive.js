var Fork = function() {
    this.state = 0;
    return this;
}

Fork.prototype.acquire = function(text,cb) {
    let fork = this, delay = 1;

    function acquire_fork() {
        if(fork.state === 0){
            fork.state = 1;
            console.log(text+" : "+delay)
            if(cb)cb();
        }
        else {
            console.log(text+" : try to after "+delay)
            delay *= 2;
            setTimeout(acquire_fork, Math.floor(Math.random() * delay));
        }
    }

    setTimeout(acquire_fork, Math.floor(Math.random() * delay));
}

Fork.prototype.release = function() {
    this.state = 0;
}

let Philosopher = function(id, forks) {
    this.id = id;
    this.forks = forks;
    this.f1 = id % forks.length;
    this.f2 = (id+1) % forks.length;
    return this;
}

Philosopher.prototype.startNaive = function(count) {
    let forks = this.forks,
        f1 = this.f1,
        f2 = this.f2,
        id = this.id;

    let acquireLeftFork = function(){
        console.log('ID: ' + id + ' Try to get left fork');
        let text='ID: ' + id + ' Get left fork'
        forks[f2].acquire(text,releaseForks,this);
    }

    let releaseForks = function (cb) {
        console.log("ID: " + id + " finished eating.");
        forks[f1].release();
        forks[f2].release();
        setTimeout(loop, Math.floor(Math.random() * 10+10));
    }


    let loop = function () {
        if (count-- > 0) {
            console.log("ID: " + id + " loop : " +count);
            setTimeout(function(){
                console.log('ID: ' + id + ' Try to get right fork');
                forks[f1].acquire('ID: ' + id + ' Get right fork',acquireLeftFork)
            }, Math.floor(Math.random() * 10));
        }
        if(count===0){
            counter+=1;
            console.log(counter+' / '+max_counter)
        }
    };

    setTimeout(loop, Math.floor(Math.random() * 10+10));
}


let counter=0;
let max_counter=0;
var N = 5;
var forks = [];
var philosophers = []
for (var i = 0; i < N; i++) {
    forks.push(new Fork());
}

for (var i = 0; i < N; i++) {
    philosophers.push(new Philosopher(i, forks));
}

for (var i = 0; i < N; i++) {
    philosophers[i].startNaive(10);
    max_counter+=1;
}



