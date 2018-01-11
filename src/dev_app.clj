(ns dev-app
  (:require
    [compojure.core :refer [defroutes GET]]
    [compojure.route :as route]
    [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
    [ring.util.response :as response]
    [tailrecursion.ring-proxy :refer [wrap-proxy]]))


(defroutes app-routes
           (route/resources "/" {:root "public"})
           #(GET "/" [] (response/content-type
                          (response/resource-response "index.html" {:root "public"})
                          "text/html"))
           (route/not-found "Nenalezeno"))


(def app (-> app-routes
             (wrap-defaults site-defaults)
             ;; Django development server API:
             (wrap-proxy "/api" "http://localhost:8000/api")
             ;; Django media files
             (wrap-proxy "/static" "http://localhost:8000/media")))
