import {NgrxValueConverter} from "ngrx-forms";

export class DateToUtcConverter implements NgrxValueConverter<Date | null, string | null> {
  convertStateToViewValue(value: string | null): Date | null {
    return value ? new Date(value) : null;
  }

  convertViewToStateValue(date: Date | null): string | null {
    let now = new Date();
    console.log(now);
    if (date) {
      console.log("Original Date: " + date.toString() + " - " + date.getTime());
      console.log("Offset timezone: " + date.getTimezoneOffset());

      let newDate = date.getTime() -  (date.getTimezoneOffset()*60*1000);
      let newTimestamp = new Date(newDate, );
      date.setDate(date.getDate() + 1);
      console.log("Date + 1 day: " + date.toString() + " - " + date.getTime());
      console.log("FinalDate : " + newTimestamp.toISOString() + " - " + date.getTime());

      debugger;
      var timezoneOffset = date.getMinutes() + date.getTimezoneOffset();
      var timestamp = date.getTime() - timezoneOffset * 1000 *3600;
      var correctDate = new Date(timestamp);
      correctDate.setUTCHours(0, 0, 0, 0);
      console.log(correctDate.toISOString());
      return correctDate.toISOString();

    }


    return null;
  }

}
