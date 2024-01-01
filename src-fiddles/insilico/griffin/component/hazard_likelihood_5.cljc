(ns insilico.griffin.component.hazard-likelihood-5)

;; 4 category system
;; - https://services.anu.edu.au/files/document-collection/assessing_ohs_risk.pdf

(def schema
  [{:db/ident ::certain
    :db/doc "Expected to occur in most circumstances"}
   {:db/ident ::very-likely
    :db/doc "Will probably occur in most circumstances"}
   {:db/ident ::possible
    :db/doc "Might occur occasionally"}
   {:db/ident ::unlikely
    :db/doc "Could happen at some time"}
   {:db/ident ::rare
    :db/doc "May happen only in exceptional circumstances"}
   ])
