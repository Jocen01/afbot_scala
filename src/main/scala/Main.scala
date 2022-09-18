import sttp.client3._
import org.json4s._
import org.json4s.native.JsonMethods._

def getHousesJson():String =
  val request = basicRequest.get(uri"https://api.afbostader.se:442/redimo/rest/vacantproducts?lang=sv_SE&type=1")
  val backend = HttpClientSyncBackend()
  val response = request.send(backend)
  val resp = response.body
  resp match {
    case Right(i) => i
    case Left(s) => "{}"
  }

@main def hello: Unit = 
  println(getHousesAtDelfi())

def getHouses():Vector[Map[String,Any]] =
  implicit val formats = org.json4s.DefaultFormats
  val produktsAndError = parse(getHousesJson()).extract[Map[String,Vector[Map[String,Any]]]]
  val product = produktsAndError("product")
  product

def getHousesAtDelfi() =
  getHouses().filter(x => x("area") == "Delfi")
def msg = "I was compiled by Scala 3. :)"
