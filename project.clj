(defproject me.flowthing/raven "0.1.6"
  :description "A simple notifications library using reagent."
  :url "http://www.github.com/eerohele/raven"
  :license "Eclipse License"
  :scm {:name "git"
        :url "https://github.com/eerohele/raven"}

  :source-paths ["src/clj" "src/cljs"]

  :plugins [[lein-environ "1.1.0"]
            [lein-garden "0.3.0"]]

  :garden {:builds [{:id "dev"
                     :pretty-print? true
                     :source-paths ["src/clj/raven/styles.clj"]
                     :stylesheet raven.styles/raven-styles
                     :compiler {:output-to "resources/public/css/raven.css"
                                :pretty-print? true}}]}

  :min-lein-version "2.5.0"

  :clean-targets ^{:protect false} [:target-path
                                    [:cljsbuild :builds :app :compiler :output-dir]
                                    [:cljsbuild :builds :app :compiler :output-to]]

  :profiles {:provided {:dependencies [[org.clojure/clojurescript "1.10.597"]
                                       [reagent "0.10.0"]]}

             :dev {:repl-options {:init-ns user
                                  :nrepl-middleware []}

                   :ring {:handler raven.handler/app
                          :uberwar-name "raven.war"}

                   :cljsbuild {:builds {:app {:source-paths ["src/cljs" "env/dev/cljs"]
                                              :compiler {:output-to "resources/public/js/app.js"
                                                         :output-dir "resources/public/js/out"
                                                         :asset-path "js/out"
                                                         :optimizations :none
                                                         :pretty-print true
                                                         :main "raven.dev"
                                                         :source-map true}}}}

                   :dependencies [[garden "1.3.9"]
                                  [cljsjs/react "16.13.0-0"]
                                  [org.clojure/clojure "1.10.1"]
                                  [environ "1.1.0"]
                                  [ring-server "0.5.0"]
                                  [ring "1.8.0"]
                                  [ring/ring-defaults "0.3.2"]
                                  [prone "2020-01-17"]
                                  [compojure "1.6.1"]
                                  [metosin/compojure-api "1.1.13"]
                                  [hiccup "1.0.5"]
                                  [secretary "1.2.3"]
                                  [com.taoensso/timbre "4.10.0"]
                                  [org.danielsz/system "0.4.5"]
                                  [http-kit "2.3.0"]
                                  [ring-mock "0.1.5"]
                                  [ring/ring-devel "1.8.0"]
                                  [leiningen-core "2.9.3"]
                                  [lein-figwheel "0.5.19"]
                                  [reagent-utils "0.3.3"]
                                  [org.clojure/tools.nrepl "0.2.13"]
                                  [pjstadig/humane-test-output "0.10.0"]]

                   :source-paths ["env/dev/clj"]
                   :plugins [[lein-figwheel "0.5.19"]
                             [lein-cljsbuild "1.1.7"]]

                   :injections [(require 'pjstadig.humane-test-output)
                                (pjstadig.humane-test-output/activate!)]

                   :figwheel {:http-server-root "public"
                              :server-port 3449
                              :nrepl-port 7002
                              :css-dirs ["resources/public/css"]
                              :ring-handler handler/app}

                   :env {:dev true}}}
  :think/meta {:type :library
               :tags [:ui]})
