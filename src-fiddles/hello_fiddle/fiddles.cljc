(ns hello-fiddle.fiddles
  (:require
   [hyperfiddle.electric :as e]
   [hyperfiddle.electric-dom2 :as dom]
   #_[insilico.griffin.app :as fe]
   [electric-tutorial.tutorial-7guis-5-crud :as tut-crud]
   [hello-fiddle.hello :refer [Hello]]
   [hello-fiddle.crud :as crud]))


;; Dev entrypoint
;; Entries will be listed on the dev index page (http://localhost:8080)
(e/def fiddles {`Hello Hello
                `tut-crud/CRUD electric-tutorial.tutorial-7guis-5-crud/CRUD
                `crud/CRUD crud/CRUD})

;; Prod entrypoint, called by `prod.clj`
(e/defn FiddleMain [ring-request]
  (e/server
    (binding [e/http-request ring-request] ; make ring request available through the app
      (e/client
        (js/console.log "> MJP - FiddleMain hello fiddles" (keys fiddles))
        (binding [dom/node js/document.body] ; where to mount dom elements
          (Hello.))))))
