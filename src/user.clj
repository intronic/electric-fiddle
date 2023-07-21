(ns user
  (:require [electric-server-java11-jetty10 :refer [start-server!]]
            [hyperfiddle.rcf :as rcf]))

(def electric-server-config 
  {:host "0.0.0.0", :port 8080, :resources-path "public", :manifest-path "public/js/manifest.edn"})

(defn main []
  (println "Starting Electric compiler and server...") ; run after REPL redirects stdout
  (def server (start-server! electric-server-config))
  (comment (.stop server)))

(comment 
  ; wait for shadow to finish
  (main)
  (rcf/enable!))
