(ns components.forms
  (:require
    [components.basic :refer [icon]]
    [utils.bem :as bem]))


(defn form-message
  [msg & [severity icon-name]]
  (when msg
    [:p {:class (bem/bm "form-message" [severity (when icon-name "with-icon")])}
     (when icon-name
       [icon icon-name [severity]])
     msg]))


(defn help-text
  [text]
  (when text
    [:p.form-group__help-text
     text]))
