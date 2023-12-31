(ns electric-fiddle.index
  (:require clojure.string
            [hyperfiddle :as hf]
            [hyperfiddle.electric :as e]
            [hyperfiddle.electric-dom2 :as dom]
            [hyperfiddle.history :as history]))

(e/defn Index []
  (e/client
    (dom/h1 (dom/text `Index))
    (dom/pre (dom/text (pr-str history/route)))
    (e/for [[k _] hf/pages #_(sort hf/pages)]
      (let [href `[~@history/route ~k]]
        (js/console.log "> MJP - Index" (name k))
        (dom/div (history/link href (dom/text (name k)))
          (dom/text " " (history/build-route history/history href)))))))
