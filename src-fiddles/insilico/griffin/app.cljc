;; (ns insilico.griffin.app
;;   (:require
;;    []
;;    ;; [datahike.api :as d]
;;    ;; [datahike.impl.entity :as de]
;;    [clojure.core.async :as async :refer [go <!]]))

;; #?(:cljs
;;    (def schema [{:db/ident       :name
;;                  :db/cardinality :db.cardinality/one
;;                  :db/index       true
;;                  :db/unique      :db.unique/identity
;;                  :db/valueType   :db.type/string}
;;                 {:db/ident       :sibling
;;                  :db/cardinality :db.cardinality/many
;;                  :db/valueType   :db.type/ref}
;;                 {:db/ident       :age
;;                  :db/cardinality :db.cardinality/one
;;                  :db/valueType   :db.type/number}
;;                 {:db/ident       :friend
;;                  :db/cardinality :db.cardinality/many
;;                  :db/valueType :db.type/ref}]))

;; #?(:cljs
;;    (def cfg-idb {:store  {:backend :indexeddb :id "idb-sandbox"}
;;                  :keep-history? false
;;                  :schema-flexibility :write
;;                  :initial-tx schema}))

;; #?(:cljs
;;    (defn setup []

;;    ;; Create an indexeddb store.
;;      (d/create-database cfg-idb)

;;    ;; Connect to the indexeddb store.
;;      (go (def conn-idb (<! (d/connect cfg-idb))))

;;    ;; Transact some data to the store.
;;      (d/transact conn-idb [{:name "Alice"
;;                             :age  26}
;;                            {:name "Bob"
;;                             :age  35
;;                             :_friend [{:name "Mike"
;;                                        :age 28}]}
;;                            {:name  "Charlie"
;;                             :age   45
;;                             :sibling [[:name "Alice"] [:name "Bob"]]}])

;;   ;; Run a query against the store.
;;      (go (println (<! (d/q '[:find ?e ?a ?v ?t
;;                              :in $ ?a
;;                              :where [?e :name ?v ?t] [?e :age ?a]]
;;                         @conn-idb
;;                         35))))

;;   ;; Use the Entity API for "Bob".
;;      (go
;;        (let [e (<! (d/entity @conn-idb 6))]
;;          (println (<! (e :name)))))

;; ;; Use the pull API
;;      (go (println (<! (d/pull @conn-idb [:name :age] 6))))
;;      (go (println (<! (d/pull-many @conn-idb '[:name :age] [5 6]))))))

;;   ;; Release the connection from the store.
;;   ;; This is necessary for deletion.
;; ;; (d/release conn-idb)

;;   ;; Delete the store.
;;   ;; This can be done immediately after creation.
;;   ;; In a fresh browser session or after releasing the connection.
;; ;; (d/delete-database cfg-idb)