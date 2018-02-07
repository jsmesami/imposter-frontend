(ns mkp.imposter.posters.db)


(def posters-per-page 12)


(def PosterFilterInitial
  {:limit posters-per-page
   :offset 0
   :since nil
   :until nil
   :bureau nil
   :spec nil})


(def PosterListInitial
  {:filter PosterFilterInitial
   :count 0
   :next? false
   :prev? false
   :list []})
