(ns insilico.griffin.component.hazard-hoc)

;; Hierachy of Control:
;; - https://en.wikipedia.org/wiki/Hierarchy_of_hazard_controls
;; AU has similar:
;; - https://www.safeworkaustralia.gov.au/system/files/documents/1901/code_of_practice_-_how_to_manage_work_health_and_safety_risks_1.pdf

(def schema
  [{:db/ident ::elimination
    :db/doc "Eliminate the hazard (aim to remove the hazard)"
    }
   {:db/ident ::substitution
    :db/doc "Replace the hazard with a safer substitute"
    }
   {:db/ident ::engineering
    :db/doc "Isolate people from the hazard with Engineering Controls"
    }
   {:db/ident ::administrative
    :db/doc "Change the way people work (Administrative Controls)"
    }
   {:db/ident ::ppe
    :db/doc "Protect the worker with Personal Protective Equipment"
    }])
