(ns failjure.spec
  (:require [failjure.core :as f]
            [clojure.spec.alpha :as s]
            [clojure.spec.gen.alpha :as gen]
            ))


(s/def ::failure (s/with-gen
                   f/failed?
                   #(gen/fmap f/->Failure (s/gen string?))))


(s/fdef f/fail
        :args (s/or
                :msg (s/cat :msg string?)
                :formatted-msg (s/cat :msg string?
                                      :rest (s/* any?)))
        :ret ::failure)

(s/fdef f/message
        :args (s/cat :it (s/or :failure ::failure
                               :ok any?))
        :ret string?)


(s/fdef f/failed?
        :args (s/or
                :failed (s/cat :it ::failure)
                :ok (s/cat :it any?))
        :ret boolean?)


(s/def ::single-binding (s/tuple simple-symbol? any?))

(s/fdef f/if-let-ok?
        :args (s/cat
               :binding ::single-binding
               :ok-branch any?
               :failed-branch (s/? any?)))

(s/fdef f/when-let-ok?
        :args (s/cat
                :binding ::single-binding
                :ok-forms (s/* any?)))

(s/fdef f/if-let-failed?
        :args (s/cat
                :binding ::single-binding
                :failed-branch any?
                :ok-branch (s/? any?)))

(s/fdef f/when-let-failed?
        :args (s/cat
                :binding ::single-binding
                :failed-branches (s/* any?)))


(s/fdef f/when-failed
        :args (s/cat
                :arglist (s/tuple simple-symbol?)
                :body any?))


(s/fdef f/attempt-all
        :args (s/cat
                :bindings :clojure.core.specs.alpha/bindings
                :return any?
                :else any?))

(s/fdef f/ok->
        :args (s/cat
                :start any?
                :forms (s/* any?)))

(s/fdef f/ok->>
        :args (s/cat
                :start any?
                :forms (s/* any?)))



(comment
  (require '[clojure.spec.test.alpha :as stest])
  (stest/instrument `f/failed?)

  (stest/instrument `f/if-let-ok?)
  (stest/instrument `f/when-failed)

  (refer 'failjure.core)
  (stest/check `f/failed?)

  (f/attempt-all [x (f/fail "Hey")]
                 "OK"
                 (f/when-failed [e] "BAD"))
  (f/if-let-ok? [x (f/fail "test")]
              "yay"
              )


  )
