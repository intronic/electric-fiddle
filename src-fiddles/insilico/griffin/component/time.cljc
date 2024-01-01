(ns insilico.griffin.component.time)

;; See:
;; - https://github.com/elm/time/blob/master/src/Time.elm
;; - https://github.com/evancz/time-zone-proposal
;; - https://guide.elm-lang.org/effects/time.html
;; - https://www.iana.org/time-zones
;;
;; - instant in time
;; - stored as number of milliseconds since the epoch (POSIX seconds + milliseconds, since 1/1/1970)
;; - Zone Name: IANA time zone string
;;   - https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Intl/DateTimeFormat/resolvedOptions
;; - Zone Offset: integer offset of minutes to UTC
;;   - https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Date/getTimezoneOffset

(def schema
  [{:db/ident ::instant
    :db/valueType :db.type/inst
    :db/cardinality :db.cardinality/one
    :db/doc "Instant in time (UTC, millisecond resolution)"}

   {:db/ident ::zone-name
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one
    :db/doc "Time Zone string if available (IANA)"}

   {:db/ident ::zone-offset
    :db/valueType :db.type/long
    :db/cardinality :db.cardinality/one
    :db/doc "Time Zone Offset in minutes from UTC (if zone not available)"}])
