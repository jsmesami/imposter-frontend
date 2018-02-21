(defproject imposter-frontend "1.0.2"
  :description "Imposter is a poster generation tool for Municipal Library of Prague"

  :url "https://github.com/jsmesami/imposter-frontend"

  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :min-lein-version "2.8.1"

  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.946"]
                 [org.clojure/core.async  "0.4.474"]
                 [cljs-ajax "0.7.3"]
                 [com.cemerick/url "0.1.1"]
                 [reagent "0.7.0"]
                 [reagent-utils "0.3.0"]
                 [re-frame "0.10.5"]
                 [day8.re-frame/http-fx "0.1.5"]]

  :plugins [[lein-cljsbuild "1.1.7" :exclusions [[org.clojure/clojure]]]
            [lein-doo "0.1.8"]
            [lein-figwheel "0.5.14"]
            [lein-kibit "0.1.6"]]

  :profiles {:dev {:dependencies [[org.clojure/tools.nrepl "0.2.13"]
                                  [figwheel-sidecar "0.5.14"]
                                  [com.cemerick/piggieback "0.2.2"]
                                  [binaryage/devtools "0.9.9"]
                                  [doo "0.1.8"]
                                  [day8.re-frame/test "0.1.5"]
                                  [day8.re-frame/re-frame-10x "0.2.0"]
                                  ;; Development webserver:
                                  [clj-http "3.7.0"]
                                  [compojure "1.6.0"]
                                  [ring "1.6.2"]
                                  [ring/ring-defaults "0.3.1"]
                                  [tailrecursion/ring-proxy "2.0.0-SNAPSHOT"]]}}

  :source-paths ["src"]

  :clean-targets ^{:protect false} ["resources/public/js" "target"]

  :figwheel {:http-server-root "public"
             :repl false
             :nrepl-port 7002
             :nrepl-middleware ["cemerick.piggieback/wrap-cljs-repl"]
             :server-port 3449
             :css-dirs ["resources/public/css"] ;; watch and update CSS
             :ring-handler dev-app/app}

  :doo {:paths {:karma "node_modules/karma/bin/karma"}}

  :cljsbuild {:builds
              [{:id "dev"
                :source-paths ["src"]

                ;; The presence of a :figwheel configuration here will cause figwheel to inject the figwheel client
                ;; into your build
                :figwheel {:on-jsload "mkp.imposter.core/on-js-reload"}
                           ;:open-urls ["http://localhost:3449"]}

                :compiler {:main mkp.imposter.core
                           :parallel-build true
                           :asset-path "js/out/dev"
                           :output-to "resources/public/js/imposter.js"
                           :output-dir "resources/public/js/out/dev"
                           :source-map-timestamp true
                           :closure-defines {"re_frame.trace.trace_enabled_QMARK_" true}
                           ;; To console.log CLJS data-structures make sure you enable devtools in Chrome
                           ;; https://github.com/binaryage/cljs-devtools
                           :preloads [devtools.preload day8.re-frame-10x.preload mkp.imposter.preload]}}

               {:id "min"
                :source-paths ["src"]
                :compiler {:main mkp.imposter.core
                           :parallel-build true
                           :output-to "resources/public/js/imposter.js"
                           :output-dir "resources/public/js/out/min"
                           :optimizations :advanced
                           :pretty-print false}}
               {:id "test"
                :source-paths ["src"]
                :compiler {:output-to "resources/public/js/imposter.js"
                           :output-dir "resources/public/js/out/test"
                           :main tests.core
                           :optimizations :none}}]}

  :aliases {"build" ["do" "clean," "cljsbuild" "once" "min"]
            "dev" ["figwheel" "dev"]
            "test" ["doo" "firefox" "test" "once"]})
