(ns hello-fiddle.crud
  (:require [clojure.string :as str]
            [missionary.core :as m]
            [hyperfiddle.electric :as e]
            [hyperfiddle.electric-dom2 :as dom]
            [hyperfiddle.electric-ui4 :as ui4]))


(def !state (atom {:selected nil
                   :stage {:name ""
                           :surname ""}
                   :names (sorted-map
                            0 {:name "Emil", :surname "Hans"}
                            1 {:name "Bob", :surname "McBob"}
                            2 {:name "Fred", :surname "McFred"})}))

(def next-id (partial swap! (atom (apply max (keys (:names @!state)))) inc))

(defn select! [id]
  (swap! !state (fn [state]
                  (assoc state :selected id
                    :stage (get-in state [:names id])))))

(defn set-name! [name]
  (swap! !state assoc-in [:stage :name] name))

(defn set-surname! [surname]
  (swap! !state assoc-in [:stage :surname] surname))

(defn create! []
  (swap! !state (fn [{:keys [stage] :as state}]
                  (-> state
                    (update :names assoc (next-id) stage)
                    (assoc :stage {:name "", :surname ""})))))
(defn delete! []
  (swap! !state (fn [{:keys [selected] :as state}]
                  (update state :names dissoc selected))))

(defn update! []
  (swap! !state (fn [{:keys [selected stage] :as state}]
                  (assoc-in state [:names selected] stage))))

(defn filter-names [names-map needle]
  (if (empty? needle)
    names-map
    (let [needle (str/lower-case needle)]
      (reduce-kv (fn [r k {:keys [name surname]}]
                   (if (or (str/includes? (str/lower-case name) needle)
                         (str/includes? (str/lower-case surname) needle))
                     r
                     (dissoc r k)))
        names-map names-map))))

;; https://tailwindui.com/components/application-ui/forms/form-layouts

;; <div class= "relative" >
;; <label for= "name" class= "absolute -top-2 left-2 inline-block bg-white px-1 text-xs font-medium text-gray-900" >Name</label>
;; <input type= "text" name= "name" id= "name" class= "block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6" placeholder= "Jane Smith" >
;; </div>

(e/defn InputComponent [!field field field-name label input-prefix input-prefix-class label-type]
  (e/client
    (let [field-sym (gensym field-name)]
      (dom/div (dom/props {:class "mt-10 grid grid-cols-1 gap-x-6 gap-y-8 sm:grid-cols-6"})
        (dom/div (dom/props {:class "sm:col-span-4"})
          (dom/div (dom/props {:class (condp = label-type :above "", :overlapping "relative", "")})
            (if (= label-type :above)
              (dom/label (dom/props {:style {:grid-area "a"}
                                     :for (name field-sym)
                                     :class "block text-sm font-medium leading-6 text-gray-900"})
                (dom/text label))
              (dom/text ""))
            (dom/div (dom/props {:class "mt-2"})
              (dom/div (dom/props {:class "flex rounded-md shadow-sm ring-1 ring-inset ring-gray-300 focus-within:ring-2 focus-within:ring-inset focus-within:ring-indigo-600 sm:max-w-md"})
                (dom/span (dom/props {:class "flex select-none items-center pl-3 text-gray-500 sm:text-sm"})
                  (dom/div (dom/props {:class input-prefix-class}) (dom/text input-prefix))) ; prefix string/icon in the input entry field
                (ui4/input field (e/fn [v] (reset! !field v))
                  (dom/props {:id (name field-sym)
                              :class (condp = label-type
                                       :above "block flex-1 border-0 bg-transparent py-1.5 pl-1 text-gray-900 placeholder:text-gray-400 focus:ring-0 sm:text-sm sm:leading-6"
                                       :overlapping "block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                                       "block flex-1 border-0 bg-transparent py-1.5 pl-1 text-gray-900 placeholder:text-gray-400 focus:ring-0 sm:text-sm sm:leading-6")}))))))))))

;; not sure why this gives:
;; ERROR hyperfiddle.electric: #error {
;; :cause Unbound electric var `hyperfiddle.electric-dom2/node`
#_(e/def search-icon (e/client (dom/div (dom/props {:class "rotate-45"}) (dom/text "⚲"))))

;; this doesnt compile because: I cannot resolve `hyperfiddle.electric-dom2/new-node`, maybe it's defined only on the client?
#_(e/def search-icon (dom/div (dom/props {:class "rotate-45"}) (dom/text "⚲")))

(e/defn CRUD []
  (e/client
    (let [state (e/watch !state)
          selected (:selected state)]
      (dom/form (dom/props {})
        (dom/div (dom/props {:class "space-y-12"})
          (dom/div (dom/props {:class "border-b border-gray-900/10 pb-12"})
            (let [!needle (atom "")
                  needle (e/watch !needle)]
              (dom/h1 (dom/text "HELLO"))
              ;; (search-icon.)
              #_(InputComponent. !needle needle "needle" "Search" search-icon)
              (InputComponent. !needle needle "needle" "Search" "⚲" "rotate-45" #_:above :overlapping)
              (dom/div (dom/props {:class "mt-8 flow-root"})
                (dom/div (dom/props {:class "-mx-4 -my-2 overflow-x-auto sm:-mx-6 lg:-mx-8"})
                  (dom/div (dom/props {:class "inline-block min-w-full py-2 align-middle sm:px-6 lg:px-8"})
                    (dom/div (dom/props {:class "overflow-hidden shadow ring-1 ring-black ring-opacity-5 sm:rounded-lg"})
                      (dom/table (dom/props {:class "min-w-full divide-y divide-gray-300"})
                        (dom/thead (dom/props {:class "bg-gray-50"})
                          (dom/tr
                            (dom/th (dom/props {:scope "col" :class "py-3.5 pl-4 pr-3 text-left text-sm font-semibold text-gray-900 sm:pl-6"})
                              (dom/text "Surname"))
                            (dom/th (dom/props {:scope "col" :class "px-3 py-3.5 text-left text-sm font-semibold text-gray-900"})
                              (dom/text "Name"))))
                        (dom/tbody (dom/props {:class "divide-y divide-gray-200 bg-white"})
                          (e/for [entry (filter-names (:names state) needle)]
                            (let [id (key entry)
                                  value (val entry)]
                              (dom/tr (dom/props {:style {:cursor :pointer
                                                          :color (if (= selected id) :white :inherit)
                                                          :background-color (if (= selected id) :blue :inherit)
                                                          :padding "0.1rem 0.5rem"}})
                                (dom/td (dom/props {:class "whitespace-nowrap py-4 pl-4 pr-3 text-sm font-medium text-gray-900 sm:pl-6"})
                                  (dom/text (:surname value)))
                                (dom/td (dom/props {:class "whitespace-nowrap px-3 py-4 text-sm text-gray-500"})
                                  (dom/text (:name value)))
                                (dom/on "click" (e/fn [_] (select! id))))))))))))))
          (let [stage (:stage state)]
            (dom/span (dom/props {:style {:grid-area "e"}}) (dom/text "Name:"))
            (ui4/input (:name stage) (e/fn [v] (set-name! v))
              (dom/props {:style {:grid-area "f"}}))
            (dom/span (dom/props {:style {:grid-area "g"}}) (dom/text "Surname:"))
            (ui4/input (:surname stage) (e/fn [v] (set-surname! v))
              (dom/props {:style {:grid-area "h"}})))
          (dom/div (dom/props
                     {:style {:grid-area "j"
                              :display :grid
                              :grid-gap "0.5rem"
                              :grid-template-columns "auto auto auto 1fr"}})
            (ui4/button (e/fn [] (create!)) (dom/text "Create"))
            (ui4/button (e/fn [] (update!)) (dom/text "Update")
              (dom/props {:disabled (not selected)}))
            (ui4/button (e/fn [] (delete!)) (dom/text "Delete")
              (dom/props {:disabled (not selected)}))))))))
