(ns insilico.fiddles
  (:require [hyperfiddle.electric :as e]
            [hyperfiddle.electric-dom2 :as dom]
            [insilico.user :refer [UserReg]]))

(e/defn MyFiddle []
  (e/client
    (dom/h1 (dom/text "Hello from my fiddle."))))

(e/def fiddles ; Entries for the dev index
  {`MyFiddle MyFiddle
   `UserReg UserReg})

(e/defn FiddleMain [ring-req] ; prod entrypoint
  (e/server
    (binding [e/http-request ring-req])
    (e/client
      (binding [dom/node js/document.body]
        (MyFiddle.)))))