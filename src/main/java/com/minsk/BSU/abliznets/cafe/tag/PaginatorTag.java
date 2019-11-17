package com.minsk.BSU.abliznets.cafe.tag;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class PaginatorTag extends TagSupport {
    private int pageCount;
    private int previewPageCount = 4;
    private int currentPageNumber;

    private int recordsCount;
    private String command;

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public void setPreviewPageCount(int previewPageCount) {
        this.previewPageCount = previewPageCount;
    }

    public void setCurrentPageNumber(int currentPageNumber) {
        this.currentPageNumber = currentPageNumber;
    }

    public void setRecordsCount(int recordsCount) {
        this.recordsCount = recordsCount;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            JspWriter writer = pageContext.getOut();
            writer.write("<div class=\"paginator\">");
            writer.write(getPreviousPageButtonText());
            if (1 < currentPageNumber) {
                writer.write(getFirstPageButtonText());
            }
            writer.write(getButtonsText());
            if (pageCount > previewPageCount + currentPageNumber) {
                writer.write(getLastPageButtonText());
            }
            writer.write(getNextPageButtonText());
            writer.write("</div>");
        } catch (IOException e) {
            throw new JspException(e);
        }

        return SKIP_BODY;
    }

    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }

    private String getPreviousPageButtonText() {
        boolean previousPageButtonDisabled = (currentPageNumber == 1);
        return getFormText(
                "&laquo;",
                currentPageNumber - 1,
                previousPageButtonDisabled,
                false
        );
    }

    private String getFirstPageButtonText() {
        return getFormText(String.valueOf(1), 1, false, false) +
                getFormText("...", 0, true, false);
    }

    private String getButtonsText() {
        StringBuilder buttonsBuilder = new StringBuilder();

        int rightBorder = findRightBorder();
        int rightCount = rightBorder - currentPageNumber;
        int missingCount = previewPageCount - rightCount;
        if (missingCount > 0) {
            int leftBorder = findLeftBorder(missingCount);
            buttonsBuilder.append(getLeftPreviewButtonsText(leftBorder));
        }

        buttonsBuilder.append(getCurrentPageButtonText());

        buttonsBuilder.append(getRightPreviewButtonsText(rightBorder));

        return buttonsBuilder.toString();
    }

    private int findLeftBorder(int missingCount) {
        int leftBorder;
        if (currentPageNumber - missingCount >= 1) {
            leftBorder = currentPageNumber - missingCount;
        } else {
            leftBorder = 1;
        }

        return leftBorder;
    }

    private int findRightBorder() {
        int rightBorder;
        if (currentPageNumber + previewPageCount < pageCount) {
            rightBorder = currentPageNumber + previewPageCount;
        } else {
            rightBorder = pageCount;
        }

        return rightBorder;
    }

    private String getLeftPreviewButtonsText(int leftBorder) {
        StringBuilder buttonsBuilder = new StringBuilder();
        for (int i = leftBorder; i < currentPageNumber; i++) {
            buttonsBuilder.append(getFormText(
                    String.valueOf(i),
                    i,
                    false,
                    false)
            );
        }

        return buttonsBuilder.toString();
    }

    private String getCurrentPageButtonText() {
        return getFormText(
                String.valueOf(currentPageNumber),
                currentPageNumber,
                true,
                true
        );
    }

    private String getRightPreviewButtonsText(int rightBorder) {
        StringBuilder buttonsBuilder = new StringBuilder();
        for (int i = currentPageNumber + 1; i <= rightBorder; i++) {
            buttonsBuilder.append(getFormText(
                    String.valueOf(i),
                    i,
                    false,
                    false)
            );
        }

        return buttonsBuilder.toString();
    }

    private String getLastPageButtonText() {
        return getFormText("...", 0, true, false) +
                getFormText(String.valueOf(pageCount), pageCount, false, false);
    }

    private String getNextPageButtonText() {
        boolean nextPageButtonDisabled = (currentPageNumber == pageCount);
        return getFormText(
                "&raquo;",
                currentPageNumber + 1,
                nextPageButtonDisabled,
                false
        );
    }

    private String getFormText(String buttonText, int pageNumber,
                               boolean isButtonDisabled, boolean isButtonActive) {
        ServletContext context = pageContext.getServletContext();
        String path = context.getContextPath();
        StringBuilder formBuilder = new StringBuilder(
                "<form action=\"" + path + "/command\" method=\"post\">" +
                        "<input type=\"hidden\" name=\"command\" value=\"" + command + "\">" +
                        "<input type=\"hidden\" name=\"navigationWay\" value=\"forward\">" +
                        "<input type=\"hidden\" name=\"pageNumber\" value=\"" + pageNumber + "\">" +
                        "<input type=\"hidden\" name=\"recordsCount\" value=\"" + recordsCount + "\">" +
                        "<button type=\"submit\" "
        );
        if (isButtonDisabled) {
            formBuilder.append("disabled ");
        }
        if (isButtonActive) {
            formBuilder.append("class=\"active\"");
        }
        formBuilder.append(">").append(buttonText).append("</button>");
        formBuilder.append("</form>");

        return formBuilder.toString();
    }
}
