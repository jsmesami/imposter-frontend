(ns mkp.imposter.home.db
  (:require
    [cemerick.url :refer [url-encode]]))


(def posters-per-page 12)


(def PosterFilterInitial
  {:limit posters-per-page
   :offset 0
   :since nil
   :until nil
   :bureau nil
   :spec nil})


(def HomeViewInitial
  {:posters {:filter PosterFilterInitial
             :count 0
             :next? false
             :prev? false
             :list []}})
