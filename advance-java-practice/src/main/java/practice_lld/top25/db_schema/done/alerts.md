





@Metrics
    metric_id
    metric_name
    source
    value
    tag
    timestamp

@Alerts
    alert_id
    metric_id
    message
    severity(p0,p1,p2)
    status(open, ack, resolved)
    resolved_at
    created_at

API endpoints

POST v1/api/metrics
    Body{
        metric_name
        source
        value
        tag
    }
    Response {
         status: "success",
         metric_id: "12234"
    }

GET v1/api/metrics?tag=erorr&source=host&metricName=test
  
Active GET v1/alerts?page=1&limit=50&status=open

https://ombharatiya.medium.com/system-design-interviews-toolkit-80fd45688281
