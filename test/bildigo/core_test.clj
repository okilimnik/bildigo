(ns bildigo.core-test
  (:require
   [clojure.test :refer [deftest is]]
   [bildigo.core :refer [app]]
   [bildigo.date :as date]
   [cheshire.core :as json]
   [ring.mock.request :as mock]))

(deftest hello-handler-test
  (with-redefs [date/iso-8601-format (constantly "")]
    (is (= (app (mock/request :get "/hello"))
           {:status  200
            :headers {"Content-Type" "application/json; charset=utf-8"}
            :body    (json/generate-string {:greeting "Hello Anonymous!"
                                            :timestamp ""})}))
    (is (= (app (mock/request :get "/hello?name=Abdullah"))
           {:status  200
            :headers {"Content-Type" "application/json; charset=utf-8"}
            :body    (json/generate-string {:greeting "Hello Abdullah!"
                                            :timestamp ""})}))))

(deftest bye-handler-test
  (is (= (app (mock/request :get "/bye"))
         {:status  200
          :headers {"Content-Type" "text/plain"}
          :body    "Goodbye!"})))
