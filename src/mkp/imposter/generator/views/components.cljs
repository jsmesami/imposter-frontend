(ns mkp.imposter.generator.views.components)


(defn char-counter
  [text char_limit]
  (when char_limit
    [:small.form-text.text-muted
     "Vyplněno "
     [:strong (count text)]
     " znaků z "
     [:strong char_limit]
     " dostupných."]))
