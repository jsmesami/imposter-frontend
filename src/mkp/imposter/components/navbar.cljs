(ns mkp.imposter.components.navbar
  (:require
    [mkp.imposter.utils.bem :as bem]))


(def module-name "navbar")


(defn navbar
  [& children]
  [:div.row {:class module-name}
   (->> children
        (into [:div.col-sm-7
               {:class (bem/be module-name "children")}]))
   [:div.col-sm-5.d-none.d-sm-block
    {:class (bem/be module-name "logo")}
    [:img {:src "/assets/images/logo_MLP.svg"
           :alt "Městská knihovna v Praze"}]]])
