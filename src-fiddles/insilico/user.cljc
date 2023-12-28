(ns insilico.user
  (:require [hyperfiddle.electric :as e]
            [hyperfiddle.electric-dom2 :as dom]))

;; for display use typeid strategy with <schema>_<ULID>
;; where schema is name of :person and ULID is ULID format of SQUUID (crockford base32)

(def schema
  [{:db/ident :person/id,
    :db/valueType {:db/ident :db.type/uuid},
    :db/cardinality {:db/ident :db.cardinality/one} :db/unique :db.unique/identity}
   {:db/ident :person/fname
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one}
   {:db/ident :person/lname
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one}
   {:db/ident :person/email
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one
    :db/unique :db.unique/identity}
   {:db/ident :person/phone
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one
    :db/unique :db.unique/identity}
   ])

(e/defn UserReg []
  (e/client
    (dom/h1 (dom/text "User"))))

