(ns mkp.imposter.generator.views.components)


(defn help-text
  [text]
  (when text
    [:small.form-text.text-muted.help-text
     text]))


(defn char-counter
  [text char_limit]
  (when char_limit
    [:small.form-text.text-muted
     "Vyplněno "
     [:strong (count text)]
     " znaků z "
     [:strong char_limit]
     " dostupných."]))


(defn error-msg
  [error]
  (when error
    [:small.form-text.is-invalid error]))
