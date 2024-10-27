package clock

import "time"

func Now() time.Time {
	return time.Now().UTC()
}

func GetUtc(timePtr *time.Time) *time.Time {
	if timePtr == nil {
		return nil
	}

	utc := timePtr.UTC()
	return &utc
}
