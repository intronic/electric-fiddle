(ns insilico.griffin.component.person)


(def schema
  [{:db/ident ::id
    :db/valueType :db.type/uuid
    :db/cardinality :db.cardinality/one
    :db/doc "Person Id"}

   {:db/ident ::first-name
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one
    :db/doc "First Name"}

   {:db/ident ::last-name
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one
    :db/doc "Last Name"}])
