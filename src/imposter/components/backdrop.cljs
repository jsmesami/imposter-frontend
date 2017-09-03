(ns imposter.components.backdrop
  (:require
    [clojure.string :as string]
    [goog.dom.classes :as classes]
    [reagent.core :as reagent]))


(defn backdrop
  [& content]
  (reagent/create-class
    {:display-name
     "backdrop"

     :component-will-mount
     #(classes/add (-> js/window .-document .-body) "backdrop")

     :component-will-unmount
     #(classes/remove (-> js/window .-document .-body) "backdrop")

     :reagent-render
     (fn render-backdrop [& content]
       [:div
        [:div.backdrop]
        (into [:div.backdrop__content] content)])}))
