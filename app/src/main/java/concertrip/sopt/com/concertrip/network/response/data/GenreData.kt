package concertrip.sopt.com.concertrip.network.response.data

import concertrip.sopt.com.concertrip.model.Artist
import concertrip.sopt.com.concertrip.model.Concert

data class GenreData(
    var _id : String?,
    var profileImg : String?,
    var backImg : String?,
    var name : String?,
    var isSubscribe : Boolean?,
    var subscribeNum : Int?,
    var youtubeUrl : String?,
    var eventList : List<SimpleConcertData>?
){
    fun toArtist() : Artist {
        val a =  Artist(_id = _id?:"0")

        a.profileImg =  profileImg?:""
        a.backImg = backImg?:""
        a.name = name?:"서버 에서 안왔습니다."
        a.subscribe = isSubscribe?:false
        a.subscribeNum = subscribeNum?:0
        a.youtubeUrl = youtubeUrl?:""

        val cList = ArrayList<Concert>()
        eventList?.forEach{
            cList.add(it.toConcert())
        }
        a.concertList = cList

        return a
    }



    override fun toString(): String ="ArtistData{\n" +
            "_id : $_id\n" +
            "profileImg : $profileImg\n" +
            "backImg : $backImg\n " +
            "name : $name\n" +
            "subscribe : $isSubscribe" +
            "subscribeNum : $subscribeNum\n" +
            "youtubeUrl : $youtubeUrl\n" +
            "eventList : $eventList\n" +
            "}"
}
