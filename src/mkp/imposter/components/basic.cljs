(ns mkp.imposter.components.basic
  (:require
    [clojure.string :refer [join]]
    [mkp.imposter.utils.bem :refer [bem] :as bem]))


(defn svg
  [id]
  (let [icon-name (name id)]
    [:svg {:role "img"}
     [:use {:xlink-href (str "/assets/icons/" icon-name ".svg#" icon-name)}]]))


(defn icon
  [id & modifiers]
  [:span
   {:class (bem/bm "icon" (into [id] modifiers))}
   [svg id]])


(defn button
  [label & {:keys [enabled? busy? on-click attrs modifiers icon-name]
            :or {enabled? true
                 busy? false
                 attrs {}
                 modifiers []}}]
  (let [states [(when-not enabled? "disabled") (when busy? "busy")]
        default-attrs {:class (->> (bem/bm "btn" modifiers)
                                   (conj states)
                                   (join " "))
                       :on-click #(when (and (not busy?) enabled? on-click)
                                    (on-click %))}]
    [:button
     (merge default-attrs attrs)
     label

     (if busy?
       [icon "spinner" "spinning"]
       (when icon-name [icon icon-name]))]))


(defn input
  [& {:keys [enabled? type value on-change on-key-press attrs modifiers]
      :or {enabled? true
           type :text
           attrs {}
           modifiers []}}]
  (let [default-attrs {:default-value value
                       :class "form-control"
                       :on-change #(when (and enabled? on-change)
                                     (on-change (-> % .-target .-value)))
                       :on-key-press #(when (and enabled? on-key-press)
                                        (on-key-press %))
                       :disabled (when-not enabled? "disabled")
                       :type type}]
    [:div
     [:input (merge default-attrs attrs)]]))
