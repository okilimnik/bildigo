(ns bildigo.date
  (:import [java.util TimeZone]
           [java.text SimpleDateFormat]))

(def formatter (doto (SimpleDateFormat. "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
                 (.setTimeZone (TimeZone/getTimeZone "CET"))))

(defn format! [date]
  (.format formatter date))