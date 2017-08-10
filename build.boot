
(set-env!
  :source-paths #{"src" "test"}
  :dependencies '[[org.clojure/clojure "1.9.0-alpha17" :scope "provided"]
                  [org.clojure/spec.alpha "0.1.123" :scope "provided"]
                  [org.clojure/test.check "0.10.0-alpha2" :scope "test"]

                  [failjure "1.0.1"]

                  [adzerk/bootlaces "0.1.13" :scope "test"]
                  [adzerk/boot-test "1.1.1" :scope "test"]])

(require '[adzerk.bootlaces :refer :all]
         '[adzerk.boot-test :refer :all])

(def +version+ "0.1.0-SNAPSHOT")
(bootlaces! +version+)


(task-options!
  pom {:project        'failjure-spec
       :version        +version+
       :description    "Specs for failjure"
       :url            "https://github.com/adambard/failjure-spec"
       :scm            {:url "https://github.com/adambard/failjure-spec"}
       :license        {"Eclipse Public License" "http://www.eclipse.org/legal/epl-v10.html"}})


(deftask deploy-clojars []
  (comp
    (build-jar)
    (push-release)))
