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

(defn- hello-handler
  "Returns a JSON response. Can optionally take a <name> parameter."
  [{params :params}]
  (response {:greeting (format "Hello %s!" (get params "name" "Anonymous"))
             :timestamp (date/iso-8601-format (Date.))}))

(defn- bye-handler
  "Returns a plain text response. Takes no parameters."
  [_]
  (content-type (response "Goodbye!") "text/plain"))

(defroutes routes
  (GET "/hello" [] hello-handler)
  (GET "/bye" [] bye-handler)
  (route/not-found "<h1>Page not found</h1>"))

(def app
  (-> routes
      ;; middleware
      wrap-params
      wrap-json-response))

(defn -main
  "Starts a simple HTTP server, with only 2 routes: /hello and /bye."
  [& _args]
  (run-jetty app {:port env/PORT}))