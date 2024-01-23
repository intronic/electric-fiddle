(ns hello-fiddle.hello-fe
  (:require-macros [hyperfiddle.electric-dom2 :refer [div]])
  (:require
   [missionary.core :as m]
   [hyperfiddle.electric :as e]
   [hyperfiddle.electric-dom2 :as dom]
   [hyperfiddle.electric-ui4 :as ui4]
   [clojure.string :as str]))

(e/def my-icon
  (e/client (dom/div (dom/props {:class "rotate-45 bg-lime-300"}) (dom/text "⚲"))))



; (e/def search-icon (e/client (dom/div (dom/props {:class "rotate-45"}) (dom/text "⚲"))))

(defn breakpoint
  "Returns a flow of `k` when `selector` of `resolution` `unit` is matched,
   or `nil` if it's not matched.

   `unit` defaults to \"px\""
  ([selector k resolution]
   (breakpoint selector k resolution "px"))
  ([selector k resolution unit]
   (let [mm    (.matchMedia js/window (str "(" selector ": " resolution unit ")"))
         match (fn [^js obj]
                 (when (.-matches obj) k))]
     (m/reductions
       {} ; discard
       (match mm) ; init val
       (m/observe (fn [!]
                    (let [f #(! (match %))]
                      (.addEventListener mm "change" f)
                      #(.removeEventListener mm "change" f))))))))

(defn min-width
  [k resolution-px]
  (breakpoint "min-width" k resolution-px))

(defn media-queries
  [& queries]
  (m/relieve {} ; discard
    (apply m/latest (fn [& values]
                      (into #{} (filter some?) values))
      queries)))

(e/def tailwind
  "A flow of sets containing the currently activated Tailwind breakpoints."
  (e/client
    (new (media-queries
      (min-width :sm 640)
      (min-width :md 768)
      (min-width :lg 1024)
      (min-width :xl 1280)
      (min-width :2xl 1536)))))
