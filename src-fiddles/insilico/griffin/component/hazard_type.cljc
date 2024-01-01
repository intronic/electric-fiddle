(ns insilico.griffin.component.hazard-type)

(def schema

;; for hazard type list, see: Table 1: Examples of common hazards
;; - https://www.safeworkaustralia.gov.au/system/files/documents/1901/code_of_practice_-_how_to_manage_work_health_and_safety_risks_1.pdf

  [
   {:db/ident ::manual-task
    :db/doc "Musculoskeletal disorders such as damage to joints, ligaments and muscles"}
   {:db/ident ::gravity
    :db/doc "Fractures, bruises, lacerations, dislocations, concussion, permanent injuries or death"}
   {:db/ident ::psychosocial
    :db/doc "Psychological or physical injury or illness"}
   {:db/ident ::electricity
    :db/doc "Shock, burns, damage to organs and nerves leading to permanent injuries or death"}
   {:db/ident ::machinery-and-equipment
    :db/doc "Fractures, bruises, lacerations, dislocations, permanent injuries or death"}
   {:db/ident ::hazardous-chemicals
    :db/doc "Respiratory illnesses, cancers or dermatitis"}
   {:db/ident ::extreme-temperatures
    :db/doc "Burns and heat stroke or injuries due to fatigue; hypothermia or frost bite"}
   {:db/ident ::noise
    :db/doc "Permanent hearing damage"}
   {:db/ident ::radiation
    :db/doc "Burns, cancer or blindness"}
   {:db/ident ::biological
    :db/doc "Hepatitis, legionnaires’ disease, Q fever, HIV/AIDS or allergies"}
   ])
