(defproject imposter-frontend "0.1.0"
  :description "Imposter is a poster generation tool for Prague Municipal Library"
  :url "https://github.com/jsmesami/imposter-frontend"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :min-lein-version "2.7.1"

  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.908"]
                 [org.clojure/core.async  "0.3.443"]
                 [binaryage/devtools "0.9.4"]
                 [cljs-ajax "0.7.2"]
                 [reagent "0.7.0"]
                 [re-frame "0.10.1"]
                 [day8.re-frame/http-fx "0.1.4"]
                 [re-frisk "0.5.0"]]

  :plugins [[lein-figwheel "0.5.13"]
            [lein-cljsbuild "1.1.7" :exclusions [[org.clojure/clojure]]]]

  :source-paths ["src"]

  :clean-targets ^{:protect false} ["resources/public/js" "target"]

  :figwheel {:css-dirs ["resources/public/css"] ;; watch and update CSS
             :repl false}

  :cljsbuild {:builds
              [{:id "dev"
                :source-paths ["src"]

                ;; The presence of a :figwheel configuration here will cause figwheel to inject the figwheel client
                ;; into your build
                :figwheel {:on-jsload "imposter.core/on-js-reload"}
                           ;:open-urls ["http://localhost:3449"]}

                :compiler {:main imposter.core
                           :parallel-build true
                           :asset-path "js/out/dev"
                           :output-to "resources/public/js/imposter.js"
                           :output-dir "resources/public/js/out/dev"
                           :source-map-timestamp true
                           ;; To console.log CLJS data-structures make sure you enable devtools in Chrome
                           ;; https://github.com/binaryage/cljs-devtools
                           :preloads [devtools.preload re-frisk.preload imposter.preload]}}

               {:id "min"
                :source-paths ["src"]
                :compiler {:main imposter.core
                           :parallel-build true
                           :output-to "resources/public/js/imposter.js"
                           :output-dir "resources/public/js/out/min"
                           :optimizations :advanced
                           :pretty-print false}}]}

  :aliases {"dev" ["do" "clean," "figwheel" "dev"]
            "build" ["do" "clean," "cljsbuild" "once" "min"]})
