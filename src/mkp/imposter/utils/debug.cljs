(ns mkp.imposter.utils.debug)


(defn log
  [label thing]
  (js/console.log label thing)
  thing)
