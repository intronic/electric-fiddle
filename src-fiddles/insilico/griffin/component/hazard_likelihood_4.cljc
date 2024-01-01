(ns insilico.griffin.component.hazard-likelihood-4)

;; 4 category system
;; - https://services.anu.edu.au/files/document-collection/assessing_ohs_risk.pdf

(def schema
  [{:db/ident ::almost-certain
    ;:db/doc ""
    }
   {:db/ident ::likely
    ;:db/doc ""
    }
   {:db/ident ::possible
    ;:db/doc ""
    }
   {:db/ident ::unlikely
    ;:db/doc ""
    }])
