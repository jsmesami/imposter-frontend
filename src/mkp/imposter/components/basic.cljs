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
  [id & {:keys [classes modifiers]
         :or {classes []}}]
  [:span
   {:class (->> (into [id] modifiers)
                (bem/bm "icon")
                (conj classes)
                (join " "))}
   [svg id]])


(defn button
  [label & {:keys [enabled? busy? on-click attrs classes modifiers icon-name]
            :or {enabled? true
                 busy? false
                 attrs {}
                 classes []
                 modifiers ["primary"]}}]
  (let [states [(when-not enabled? "disabled") (when busy? "busy")]
        default-attrs {:class (->> (bem/bm "btn" modifiers)
                                   (conj states)
                                   (into classes)
                                   (join " "))
                       :on-click #(when (and (not busy?) enabled? on-click)
                                    (on-click %))}]
    [:button
     (merge default-attrs attrs)
     label
     (if busy?
       [icon "spinner" :classes ["spinning"]]
       (when icon-name [icon icon-name]))]))


(defn input
  [& {:keys [enabled? widget type value on-change on-key-press attrs classes]
      :or {enabled? true
           widget :input
           type :text
           attrs {}
           classes []}}]
  (let [default-attrs {:value value
                       :class (join " " classes)
                       :on-change #(when (and enabled? on-change)
                                     (on-change (-> % .-target .-value)))
                       :on-key-press #(when (and enabled? on-key-press)
                                        (on-key-press %))
                       :disabled (when-not enabled? "disabled")
                       :type type}]
    [widget (merge default-attrs attrs)]))


(defn select
  [options & {:keys [enabled? value on-change attrs classes]
              :or {enabled? true
                   attrs {}
                   classes []}}]
  (let [default-attrs {:value (or value "")
                       :class (join " " classes)
                       :on-change #(when (and enabled? on-change)
                                     (on-change (-> % .-target .-value)))
                       :disabled (when-not enabled? "disabled")}]
    [:select (merge default-attrs attrs)
     (for [[text value] options]
       [:option
        {:key value
         :value value}
        text])]))


(defn select-image
  [& {:keys [enabled? on-change attrs classes]
      :or {enabled? true
           type :text
           attrs {}
           classes []}}]
  (let [default-attrs {:class (join " " classes)
                       :on-change (fn [e]
                                    (let [files (-> e .-target .-files)]
                                      (when (and enabled? on-change (pos? (.-length files)))
                                        (on-change (aget files 0)))))
                       :disabled (when-not enabled? "disabled")
                       :type "file"}]
    [:input (merge default-attrs attrs)]))
