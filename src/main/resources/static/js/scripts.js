
$(document).ready(function(){
    // Initialize the tooltip.
      $('.btn.copy').tooltip();

      $('.btn.copy').bind('click', function() {
        var input = document.querySelector('#survey-link');
        input.setSelectionRange(0, input.value.length + 1);
        try {
          var success = document.execCommand('copy');
          if (success) {
            $('.btn.copy').trigger('copied', ['Copied!']);
          } else {
            $('.btn.copy').trigger('copied', ['Copy with Ctrl-c']);
          }
        } catch (err) {
          $('.btn.copy').trigger('copied', ['Copy with Ctrl-c']);
        }
      });

      // Handler for updating the tooltip message.
      $('.btn.copy').bind('copied', function(event, message) {
        $(this).attr('title', message)
            .tooltip('fixTitle')
            .tooltip('show')
            .attr('title', "Copy to Clipboard")
            .tooltip('fixTitle');
      });

    var $clock = $('.countdown').countdown(parseInt($('.countdown').attr('data'))+10*60*1000)
              .on('update.countdown', function(event) {
                var format = '%H:%M:%S';
                $(this).html(event.strftime(format));
              })
              .on('finish.countdown', function(event) {
                $(this).parent().html('Survey Expired!');
                $('[name="submit"]').prop('disabled', true);
              });

});
