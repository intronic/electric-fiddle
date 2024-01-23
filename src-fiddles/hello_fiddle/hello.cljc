(ns hello-fiddle.hello
  #?(:cljs (:require-macros [hyperfiddle.electric-dom2 :refer [div]]))
  (:require
   #?(:cljs [hello-fiddle.hello-fe :as fe])
   [missionary.core :as m]
   [hyperfiddle.electric :as e]
   [hyperfiddle.electric-dom2 :as dom]
   [hyperfiddle.electric-ui4 :as ui4]
   [clojure.string :as str]))

#_(:cljs
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
                         #(.removeEventListener mm "change" f)))))))))

#_(:cljs
   (defn min-width
     [k resolution-px]
     (breakpoint "min-width" k resolution-px)))

#_(:cljs
   (defn media-queries
     [& queries]
     (m/relieve {} ; discard
       (apply m/latest (fn [& values]
                         (into #{} (filter some?) values))
         queries))))

#_(:cljs
   (e/def tailwind
     "A flow of sets containing the currently activated Tailwind breakpoints."
     (new (media-queries
            (min-width :sm 640)
            (min-width :md 768)
            (min-width :lg 1024)
            (min-width :xl 1280)
            (min-width :2xl 1536)))))

(e/defn Hello []
  (e/client
    (let [clazz [#_["text-red-800" "bg-red-300"]
                 ["text-amber-800" "bg-amber-300"]
                 ["2xl:text-lime-800" "2xl:bg-lime-300"]
                 ["xl:text-teal-800" "xl:bg-teal-300"]
                 ["lg:text-blue-800" "lg:bg-blue-300"]
                 ["md:text-violet-800" "md:bg-violet-300"]
                 ["sm:text-pink-800" "sm:bg-pink-300"]]
          claz [#_["text-red-800" "bg-red-300"]
                ["text-amber-800" "bg-amber-300"]
                ["text-lime-800" "bg-lime-300"]
                ["text-teal-800" "bg-teal-300"]
                ["text-blue-800" "bg-blue-300"]
                ["text-violet-800" "bg-violet-300"]
                ["text-pink-800" "bg-pink-300"]]
          media [nil "sm" "md" "lg" "xl" "2xl"]]
      #_(fe/setup)
      (dom/h1 (dom/props {:class "font-bold"})
        (dom/text "Hello world"))
      #_(println (e/for [c clazz] (str/join "/" c)))
      #_(dom/p (dom/text "font-bold bg-yellow-900 text-black-900 lg:text-red-800 lg:bg-aqua-800 md:text-green-800 md:bg-orange-500 sm:text-blue-800 mg:bg-green-300"))
      (e/for [c clazz]
        (dom/p (dom/props {:class c})
          (dom/text c)))
      (dom/p (dom/text "---"))
      (dom/p (dom/props {:class clazz})
        (dom/text clazz))
      (dom/p (dom/props {:class clazz})
        (dom/text clazz))
      (dom/p (dom/text "---"))
      (e/for [c claz]
        (dom/p (dom/props {:class c})
          (dom/text c)))
      #_(let [[scrollT scrollH clientH] (ui4/scroll-state< js/window)]
          (dom/p (dom/text "Scroll:" [scrollT scrollH clientH]))
          (dom/p (dom/text "Tailwind......:" (str fe/tailwind))))
      (dom/p (dom/text "Tailwind......:" (str fe/tailwind)))
      (dom/div (dom/props {:class "rotate-45 bg-pink-300"}) (dom/text "⚲"))
      (dom/text "hello")
      (dom/div (dom/props {:class "rotate-45 bg-lime-300"}) (dom/text "⚲"))

      #_:rcf)))
