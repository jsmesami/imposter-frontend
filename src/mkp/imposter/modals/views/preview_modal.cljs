(ns mkp.imposter.modals.views.preview-modal
  (:require
    [re-frame.core :refer [subscribe]]
    [mkp.imposter.modals.views.modal :refer [generic-modal]]
    [mkp.imposter.utils.bem :as bem]))


(def module-name "preview-poster")


(defn preview-poster
  []
  (let [form @(subscribe [:generator/form])]
    [generic-modal
     [:div {:class module-name}
      [:img.img-thumbnail.mx-auto
       {:class (bem/be module-name "thumb")
        :src   (:thumb form)}]]]))
