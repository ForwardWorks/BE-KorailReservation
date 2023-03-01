package com.forwardworks.korail.enum

enum class KorailCall(value: String) {
    //    EMAIL_REGEX = re.compile(r"[^@]+@[^@]+\.[^@]+")
//    PHONE_NUMBER_REGEX = re.compile(r"(\d{3})-(\d{3,4})-(\d{4})")
    SCHEME("https"),
    KORAIL_HOST("smart.letskorail.com"),
    KORAIL_PORT("443"),
    KORAIL_DOMAIN(String.format("%s://%s:%s", SCHEME, KORAIL_HOST, KORAIL_PORT)),
    KORAIL_MOBILE(String.format("%s/classes/com.korail.mobile", KORAIL_DOMAIN)),
    KORAIL_LOGIN(String.format("%s.login.Login", KORAIL_MOBILE)),
    KORAIL_LOGOUT(String.format("%s.common.logout", KORAIL_MOBILE)),
    KORAIL_SEARCH_SCHEDULE(String.format("%s.seatMovie.ScheduleView", KORAIL_MOBILE)),
    KORAIL_TICKETRESERVATION(String.format("%s.certification.TicketReservation", KORAIL_MOBILE)),
    KORAIL_REFUND(String.format("%s.refunds.RefundsRequest", KORAIL_MOBILE)),
    KORAIL_MYTICKETLIST(String.format("%s.myTicket.MyTicketList", KORAIL_MOBILE)),
    KORAIL_MYTICKET_SEAT(String.format("%s.refunds.SelTicketInfo", KORAIL_MOBILE)),
    KORAIL_MYRESERVATIONLIST(String.format("%s.reservation.ReservationView", KORAIL_MOBILE)),
    KORAIL_CANCEL(String.format("%s.reservationCancel.ReservationCancelChk", KORAIL_MOBILE)),
    KORAIL_STATION_DB(String.format("%s.common.stationinfo?device=ip", KORAIL_MOBILE)),
    KORAIL_STATION_DB_DATA(String.format("%s.common.stationdata", KORAIL_MOBILE)),
    KORAIL_EVENT(String.format("%s.common.event", KORAIL_MOBILE)),
    KORAIL_PAYMENT(String.format("%s/ebizmw/PrdPkgMainList.do", KORAIL_DOMAIN)),
    KORAIL_PAYMENT_VOUCHER(String.format("%s/ebizmw/PrdPkgBoucherView.do", KORAIL_DOMAIN)),
    DEFAULT_USER_AGENT("Dalvik/2.1.0 (Linux; U; Android 5.1.1; Nexus 4 Build/LMY48T)")
}