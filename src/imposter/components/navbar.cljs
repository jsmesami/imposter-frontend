(ns imposter.components.navbar
  (:require
    [imposter.utils.bem :as bem]))


(def module-name "navbar")


(defn navbar
  [& children]
  [:div {:class module-name}
   (into [:div {:class (bem/be module-name "children")} children])
   [:div {:class (bem/be module-name "logo")}
    [:img {:src "/assets/images/logo_MLP.svg"
           :alt "Městská knihovna v Praze"}]]])
