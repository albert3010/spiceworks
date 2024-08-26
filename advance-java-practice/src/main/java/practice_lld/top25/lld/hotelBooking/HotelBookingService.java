//package practice_lld.top25.lld.hotelBooking;
//
//
//import org.joda.time.DateTime;
//
//import java.util.List;
//import java.util.Locale;
//import java.util.Map;
//
//public class HotelBookingService {
//
//    Map<Integer, Hotel> hotelsMap;
//    Map<Integer, User> usersMap;
//
//
//    public HotelBookingService(Map<Integer, Hotel> hotelsMap, Map<Integer, User> usersMap) {
//        this.hotelsMap = hotelsMap;
//        this.usersMap = usersMap;
//    }
//
//    void addHotel(Hotel hotel) throws Exception {
//        int id =  hotel.getHotelId();
//        if(hotelsMap.get(id) == null){
//            hotelsMap.put(id, hotel);
//        }else {
//            throw new Exception("Hotel already exits");
//        }
//    }
//
//    List<Hotel> searchHotelBasedOnLocationDate(Location location, DateTime dateTime){
//
//            for(Hotel hotel : hotelsMap.values()){
//                hotel.
//
//            }
//    }
//
////    List<Room> bookHotel(List<Room>)
//
//
//
//
//
//}
