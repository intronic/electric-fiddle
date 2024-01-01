(ns insilico.griffin.component.hazard-potential-severity-health)

;; harm: death, injury or illness

;; How severe is the harm?
;; Could the hazard cause death, serious injuries, illness or only minor injuries requiring first aid
;;
;; https://www.safeworkaustralia.gov.au/system/files/documents/1901/code_of_practice_-_how_to_manage_work_health_and_safety_risks_1.pdf
;; Potential Severity categories:
;; death, serious injury, illness, minor injury requiring first aid

;; Where the hazard or risk has the potential to cause death, serious injury or illness, more
;;   emphasis should be given to those controls that eliminate or reduce the level of harm, than
;;   those that reduce the likelihood of harm occurring

(def schema
  [{:db/ident ::death
    :db/doc ""}
   {:db/ident ::serious-injury
    :db/doc ""}
   {:db/ident ::illness
    :db/doc ""}
   {:db/ident ::medical-treatment-injury
    :db/doc "Injury requiring medical treatment"}
   {:db/ident ::first-aid-injury
    :db/doc "Minor injury requiring first aid"}])
