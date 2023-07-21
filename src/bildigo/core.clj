(ns bildigo.core
  (:gen-class)
  (:require
   [compojure.core :refer [defroutes GET]]
   [compojure.route :as route]
   [ring.adapter.jetty :refer [run-jetty]]
   [ring.middleware.json :refer [wrap-json-response]]
   [ring.middleware.params :refer [wrap-params]]
   [ring.util.response :refer [response content-type]]
   [bildigo.date :as date]
   [bildigo.env :as env])
  (:import [java.util Date]))

(defn hello-handler [{params :params}]
  (response {:greeting (format "Hello %s" (get params "name" "Anonymous"))
             :timestamp (date/format! (Date.))}))

(defn bye-handler [_]
  (content-type (response "Goodbye!") "text/plain"))

(defroutes routes
  (GET "/hello" [] hello-handler)
  (GET "/bye" [] bye-handler)
  (route/not-found "<h1>Page not found</h1>"))

(def app
  (-> routes
      wrap-params
      wrap-json-response))

(defn -main
  [& _args]
  (run-jetty app {:port env/PORT}))