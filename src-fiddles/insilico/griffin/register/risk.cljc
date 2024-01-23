(ns insilico.griffin.register.risk
  (:require [insilico.griffin.component.time as t]
            [hyperfiddle.electric :as e]
            [hyperfiddle.electric-dom2 :as dom]))

;; Risk Register
;; - Record workplace hazard Risk Assessments

(def schema
  [{:db/ident ::id
    :db/valueType :db.type/uuid
    :db/cardinality :db.cardinality/one
    :db/doc "Risk Id"}

   {:db/ident ::person
    :db/valueType :db.type/ref
    :db/cardinality :db.cardinality/one
    :db/doc "Person identifying the risk"}

   {:db/ident ::location
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one
    :db/doc "Location of the risk"}

   {:db/ident ::time
    :db/valueType :db.type/ref
    :db/cardinality :db.cardinality/one
    :db/isComponent true
    :db/doc "Time risk recorded"}

   {:db/ident ::hazard
    :db/valueType :db.type/ref
    :db/cardinality :db.cardinality/many
    :db/isComponent true
    :db/doc "Hazard, risk and controls identified"}]
  )
