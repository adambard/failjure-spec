(defproject failjure-spec "2.1.1"
  :description "Specs for failjure"
  :url         "https://github.com/adambard/failjure-spec"
  :license     {:name "Eclipse Public License"
                :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[failjure "2.1.1"]]

  :repl-options {:init-ns failjure.core}

  :profiles
  {:provided {:dependencies [[org.clojure/clojure    "1.10.1"]
                             [org.clojure/spec.alpha "0.2.187"]]}
   :test {:dependencies [[nrepl/nrepl "0.8.3"]
                         [org.clojure/test.check "1.1.0"]]}}

  :cljsbuild
  {:builds [{:id "test"
             :source-paths ["src" "test"]
             :compiler {:output-to "target/testable.js"
                        :main failjure.runner
                        :target :nodejs
                        :optimizations :none}}]})
