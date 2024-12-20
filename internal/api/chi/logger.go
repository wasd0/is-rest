package chi

import (
	"bytes"
	"fmt"
	"github.com/go-chi/chi/v5/middleware"
	"github.com/pkg/errors"
	"github.com/wasd0/is-common/pkg/logger"
	"net/http"
	"time"
)

type LogFormatterImpl struct {
}

func (l *LogFormatterImpl) NewLogEntry(r *http.Request) middleware.LogEntry {
	from := r.RemoteAddr
	method := r.Method

	uri := r.RequestURI

	entry := LogEntryImpl{
		request: r,
		buf:     &bytes.Buffer{},
	}

	_, err := fmt.Fprintf(entry.buf, "[%s]  > %s - from: %s - ", method, uri, from)
	if err != nil {
		logger.Log().Error(err, "Failed converting Logger entry")
	}

	return &entry
}

type LogEntryImpl struct {
	request *http.Request
	buf     *bytes.Buffer
}

func (l *LogEntryImpl) Write(status, bytes int, _ http.Header, elapsed time.Duration, _ interface{}) {
	if _, err := fmt.Fprintf(l.buf, "%db in %s - %d", bytes, elapsed, status); err != nil {
		logger.Log().Error(err, "Failed converting Logger entry")
	}

	if status >= 500 {
		logger.Log().Error(errors.New("Http error"), l.buf.String())
	} else {
		logger.Log().Info(l.buf.String())
	}
}

func (l *LogEntryImpl) Panic(v interface{}, stack []byte) {
	logger.Log().Errorf(errors.New("Panic!"), "Error: %v, Stack: %s", v, string(stack))
}
