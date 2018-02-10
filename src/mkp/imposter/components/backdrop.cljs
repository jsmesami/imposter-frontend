(ns mkp.imposter.components.backdrop
  (:require
    [goog.dom.classes :as classes]
    [reagent.core :as reagent]))


(defn backdrop
  [& content]
  (reagent/create-class
    {:display-name
     "backdrop"

     :component-will-mount
     #(classes/add (-> js/window .-document .-body) "with-backdrop")

     :component-will-unmount
     #(classes/remove (-> js/window .-document .-body) "with-backdrop")

     :reagent-render
     (fn [& content]
       [:div
        [:div.backdrop]
        (into [:div.backdrop__content] content)])}))
