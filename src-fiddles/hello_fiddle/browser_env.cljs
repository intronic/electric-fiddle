(ns hello-fiddle.browser-env
  (:require
   [missionary.core :as m]
   [hyperfiddle.electric :as e]
   [hyperfiddle.electric-dom2 :as dom]
   [hyperfiddle.electric-ui4 :as ui4]
   [clojure.string :as str]))


#_(cc/defn dom-listener [node typ f opts]
    (.addEventListener node typ f (clj->js opts))
    #(.removeEventListener node typ f))


;; https://developer.mozilla.org/en-US/docs/Web/API/Navigator/onLine
(defn js-online
  "Returns a flow of browser true or false indicating current online status"
  []
  (m/reduce
    {}
    js/window.navigator.onLine
    (m/observe (fn [!]
                 (let [f #(!)])))))

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