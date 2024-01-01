(ns insilico.griffin.component.hazard
  (:require [hyperfiddle.electric :as e]
            [hyperfiddle.electric-dom2 :as dom]))

;; Griffin Schema+UI compontents for Halo

;; Hazard schema for risk control
;; To manage WHS risks you should:
;;     Identify hazards
;;     Assess risks
;;     Control risks
;;     Review control measures
;; - https://www.safeworkaustralia.gov.au/safety-topic/managing-health-and-safety/identify-assess-and-control-hazards/managing-risks

;; Model Code of Practice:
;;   - https://www.safeworkaustralia.gov.au/doc/model-code-practice-how-manage-work-health-and-safety-risks
;;;;
;; Additional common hazards for civil...
;;   - confined-spaces, etc..
;;
;; Additional hazards from safeworkaustralia:
;;   - https://www.safeworkaustralia.gov.au/safety-topic/hazards
;;
;; Hierarchy of Control categories:
;; - Elimination
;; - Substitution, isolation and engineering controls
;; - Administrative controls
;; - Personal protective equipment

(def schema
  [{:db/ident ::id
    :db/valueType :db.type/uuid
    :db/cardinality :db.cardinality/one
    :db/doc "Hazard Id"}

   ; https://www.safeworkaustralia.gov.au/glossary#hazard
   ; https://www.safeworkaustralia.gov.au/safety-topic/managing-health-and-safety/identify-assess-and-control-hazards
   {:db/ident ::description
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one
    :db/doc "Description of Hazard (A situation or thing that can cause harm to a person)"}

   ; https://www.safeworkaustralia.gov.au/glossary#risks
   {:db/ident ::risk
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one
    :db/doc "The harm (death, injury or illness) might occur due to the hazard"}

   ; https://www.safeworkaustralia.gov.au/glossary#control-measure
   {:db/ident ::control
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one
    :db/doc "An action taken to eliminate or minimise health and safety risks so far as is reasonably practicable"}

   ; see: hazard-type
   {:db/ident ::hazard-type
    :db/valueType :db.type/ref
    :db/cardinality :db.cardinality/one
    :db/doc "A common type of workplace hazard"}

   ; see: hazard-likelihood
   {:db/ident ::likelihood
    :db/valueType :db.type/ref
    :db/cardinality :db.cardinality/one
    :db/doc "Esimated likelihood of the risk occuring"
    }

   ; see: hazard-potential-severity{-4,-5}
   {:db/ident ::potential-severity ; degree-of-harm
    :db/valueType :db.type/ref
    :db/cardinality :db.cardinality/one
    :db/doc "Potential severity (degree of harm) of the hazard"}

   {:db/ident ::hierarchy-of-control
    :db/valueType :db.type/ref
    :db/cardinality :db.cardinality/one
    :db/doc "Hierarchy of control (effectiveness in eliminating risk)"}
   ])


;; TODO: dom for component
(e/defn HazardComponent []
  (e/client
    (dom/h1 (dom/text "Hazard"))
    (dom/p (dom/text "Hazards, risks and controls"))))
