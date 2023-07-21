(ns bildigo.date
  (:import
   [java.util Date TimeZone]
   [java.text SimpleDateFormat]))

(def ^:private iso-8601-formatter
  (doto (SimpleDateFormat. "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    (.setTimeZone (TimeZone/getTimeZone "CET"))))

(defn iso-8601-format
  "Formats a Java Date object in ISO 8601 format."
  [^Date date]
  (.format iso-8601-formatter date))