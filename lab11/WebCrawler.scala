package webcrawler

import org.htmlcleaner.TagNode

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.Success

object WebCrawler extends App {

  import org.htmlcleaner.HtmlCleaner

  import java.net.URL
  import scala.io.Source

  def getLink(basicUrl: String, link: String): String = {
    if (link.contains("http://") || link.contains("https://")) link
    else if (link.length > 2 && link.startsWith("//")) "http:" + link
    else basicUrl + link
  }

  def getFutureForUrls(url: String): Future[Array[TagNode]] = Future {
    val html = new HtmlCleaner().clean(new URL(url))
    val aElements = html.getElementsByName("a", true)
    aElements
  }

  def visitUrls(basicUrl: String, aAttributes: Array[TagNode], depthToGo: Int): Unit = {
    if (depthToGo >= 0) {
      println(basicUrl)
      aAttributes.foreach(element => {
        val link = element.getAttributeByName("href")
        if (link != null && link.nonEmpty && !link.isBlank) {
          val url: String = getLink(basicUrl, link)
          getFutureForUrls(url).onComplete {
            case Success(value) => visitUrls(url, value, depthToGo - 1)
            case _ => ;
          }
        }
      })
    }
  }

  val url = "http://google.com"
  val elements = new HtmlCleaner().clean(new URL(url)).getElementsByName("a", true)

  visitUrls(url, elements, 3)

  Thread.sleep(100000)
}
